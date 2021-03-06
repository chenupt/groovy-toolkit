import groovy.json.JsonSlurper

//========== !!! test2.json里不能带中文 !!! =============
//========== !!! test2.json里不能带中文 !!! =============
//========== !!! test2.json里不能带中文 !!! =============

jsonName = this.args[0]
println(jsonName)

basicFileName = this.args[1]
println(basicFileName)
// api中要求带的action。 
//requestAction = this.args[1]
//println(requestAction)

responseEntity = this.args[2]
println(responseEntity)

requestName = "${basicFileName}Request"
parserName  = "${basicFileName}Parser"
responseName = "${basicFileName.capitalize()}Response"
requestFileName =  "${requestName}.java"
parserFileName  =  "${parserName}.java"
responseFileName = "${responseName}.java"
lineSeparator = System.getProperty("line.separator");


//def reader = new FileReader('getAccountInfo.json')
def reader = new InputStreamReader(new FileInputStream("./json/" + jsonName), 'UTF-8');
ajson = new JsonSlurper().parse(reader)


println '======================================='
sb = new StringBuilder()
sb = parseJson2ResponseFileContent()
write2File(responseFileName, sb)
println '======================================='



def write2File(fileFullName, content){
    def folder = new File(basicFileName)
    // If it doesn't exist
    if( !folder.exists() ) {
      // Create all folders up-to and including B
      folder.mkdirs()
    }
    def file = new File(folder, fileFullName)
    file.withWriter{ Writer writer ->
        writer.append(content)
    }
}

//key参数是防备有JsonArray<自定义类>的， 这样可以给子Item命名
def getTypeFromWholePath(key, value){
    valueType = value.getClass()
    println(key + " : " + valueType)
    switch(valueType){
        case 'class java.lang.String':
            return 'String';
        case 'class java.lang.Boolean':
            return 'boolean';
        case 'class java.lang.Integer':
            return 'int';
		case 'class java.math.BigDecimal':
            return 'double';
        case 'class java.lang.Long':
            return 'long';
        case 'class java.util.ArrayList':
            listSubClass = value[0].getClass()
            //println "getType() : ArrayList : listSubClass = $listSubClass"
            listSubType = getTypeFromWholePath("${key}Item", value[0])
            return "ArrayList<$listSubType>"
        case 'class groovy.json.internal.LazyMap':
            //println "$key -- ${key.getClass()}"
            objectType = key.capitalize();
            return objectType;
            //return 'Object'
    }
    return 'String';
}


//自定义的类，这里可能会没有import的情况。 这时因为进入类里了才知道要import什么类进来。这个可能要手动加下。
def parseJson2ResponseFileContent(){
    sb = new StringBuilder()
    sb<<"package com.mycompany.requests;"<<lineSeparator
    sb<<lineSeparator

    sb<<"import android.text.TextUtils;"<<lineSeparator
    //sb<<"import com.mycompany.common.async_http.BaseResponse;"<<lineSeparator
    sb<<"import java.util.ArrayList;"<<lineSeparator
    sb<<"import org.json.JSONArray;"<<lineSeparator
    sb<<"import org.json.JSONException;"<<lineSeparator
    sb<<"import org.json.JSONObject;"<<lineSeparator
    sb<<lineSeparator

    sb<<"public class ${responseName} extends BaseResponse {"<<lineSeparator
    ajson.each{key, value ->
        if (key.equals("result")){
            key = responseEntity;
            def type = getTypeFromWholePath(key, value)
            sb<<"\tprivate $type $key;"<<lineSeparator
        }
    }
    sb<<lineSeparator

    sb<<"\tpublic $responseName (String jsonStr){"<<lineSeparator
    sb<<"\t\tsuper(jsonStr);"<<lineSeparator
    sb<<"\t\tif(!TextUtils.isEmpty(jsonStr)){"<<lineSeparator
    sb<<"\t\t\ttry{"<<lineSeparator        
    sb<<"\t\t\t\tJSONObject json = new JSONObject(jsonStr);"<<lineSeparator
    ajson.each{key, value ->
        if (key.equalsIgnoreCase("result")) {
            parse(sb, key, value, true)
        }
    }
    sb<<"\t\t\t} catch (JSONException e) {"<<lineSeparator
    sb<<"\t\t\t\te.printStackTrace();"<<lineSeparator
    sb<<"\t\t\t}"<<lineSeparator
    sb<<"\t\t}"<<lineSeparator
    sb<<"\t}"<<lineSeparator

    // getter & setter
    ajson.each{key, value ->
        if (key.equalsIgnoreCase("result")) {
            key = changeResultKey(key);
            def type = getTypeFromWholePath(key, value)
            if (type.equals("boolean")) {
                sb<<"\tpublic $type is${key.capitalize()}() {"<<lineSeparator           
            } else {
                sb<<"\tpublic $type get${key.capitalize()}() {"<<lineSeparator           
            }
            sb<<"\t\treturn $key;"<<lineSeparator
            sb<<"\t}"<<lineSeparator
            sb<<"\tpublic void set${key.capitalize()}($type $key) {"<<lineSeparator
            sb<<"\t\tthis.$key = $key;"<<lineSeparator
            sb<<"\t}"<<lineSeparator
        }
    }

    sb<<lineSeparator    
    sb<<"}"<<lineSeparator
}


def writeItemData2File(fkey, fvalue){
    def sb2 = new StringBuilder()
    sb2<<"package com.mycompany.model;"<<lineSeparator
    sb2<<lineSeparator

    sb2<<"import java.util.ArrayList;"<<lineSeparator
    sb2<<"import org.json.JSONArray;"<<lineSeparator
    sb2<<"import org.json.JSONException;"<<lineSeparator
    sb2<<"import org.json.JSONObject;"<<lineSeparator
    sb2<<lineSeparator

    sb2<<"public class ${fkey.capitalize()} implements Serializable{"<<lineSeparator
    fvalue.each{key, value ->
        def type = getTypeFromWholePath(key, value)
        sb2<<"\tprivate $type $key;"<<lineSeparator
    }
    sb2<<lineSeparator

    sb2<<"\tpublic ${fkey.capitalize()} (JSONObject json){"<<lineSeparator
    sb2<<"\t\tif(json != null){"<<lineSeparator
    fvalue.each{key, value ->
        parse(sb2, key, value, false)
        // sb2<<"\t\t\tif(!json.isNull(\"${key}\")) ${key} = json.opt${type.capitalize()}(\"${key}\");"<<lineSeparator   
    }

    println("---")

    sb2<<"\t\t}"<<lineSeparator
    sb2<<"\t}"<<lineSeparator
    sb2<<lineSeparator

    sb2<<"\tpublic static ArrayList<${fkey.capitalize()}> createWithJsonArray(JSONArray array) {"<<lineSeparator
    sb2<<"\t\tif(array != null){"<<lineSeparator
    sb2<<"\t\t\tint len = array.length();"<<lineSeparator
    sb2<<"\t\t\tArrayList<${fkey.capitalize()}> list = new ArrayList<${fkey.capitalize()}>();"<<lineSeparator
    sb2<<"\t\t\tfor(int i = 0 ; i < len ; i++){"<<lineSeparator
    sb2<<"\t\t\t\tJSONObject obj = array.optJSONObject(i);"<<lineSeparator
    fkey = changeResultKey(fkey);
    sb2<<"\t\t\t\t${fkey.capitalize()} oneItem = new ${fkey.capitalize()}(obj);"<<lineSeparator
    sb2<<"\t\t\t\tlist.add(oneItem);"<<lineSeparator
    sb2<<"\t\t\t}"<<lineSeparator
    sb2<<"\t\t\treturn list;"<<lineSeparator
    sb2<<"\t\t}"<<lineSeparator
    sb2<<"\t\treturn null;"<<lineSeparator
    sb2<<"\t}"<<lineSeparator
    sb2<<lineSeparator

    // getter & setter
    fvalue.each{key, value ->
        def type = getTypeFromWholePath(key, value)
        if (type.equals("boolean")) {
            sb2<<"\tpublic $type is${key.capitalize()}() {"<<lineSeparator           
        } else {
            sb2<<"\tpublic $type get${key.capitalize()}() {"<<lineSeparator           
        }
        sb2<<"\t\treturn $key;"<<lineSeparator
        sb2<<"\t}"<<lineSeparator
        sb2<<"\tpublic void set${key.capitalize()}($type $key) {"<<lineSeparator
        sb2<<"\t\tthis.$key = $key;"<<lineSeparator
        sb2<<"\t}"<<lineSeparator
    }

    sb2<<lineSeparator

    sb2<<"}"

    write2File("${fkey.capitalize()}.java", sb2)
}

def parse(sb, key, value, shouldEditResult){
    def result = key
    // 是否替换key为result
    if (shouldEditResult && key.equalsIgnoreCase("result")) {
        result = "result"       
    }
    if (shouldEditResult) {
        key = responseEntity;
    }
    def type = getTypeFromWholePath(key, value)
    if (type.startsWith("ArrayList")){
            def subtype = ""
            def pattern = ~/ArrayList<(.*)>/
            type.find(pattern){
                subtype = it[1]
            }

            sb<<lineSeparator
            sb<<"\t\t\t\tif(!json.isNull(\"${result}\")) {"<<lineSeparator
            sb<<"\t\t\t\t\tJSONArray array = json.optJSONArray(\"$result\");"<<lineSeparator
                
            
            //println "subtype = $subtype" 
            // TODO other type
            if(subtype.equals("long") || subtype.equals("int")
                || subtype.equals("String") || subtype.equals("boolean") || type.equals("double")){
                sb<<"\t\t\t\t\t$key = new $type();"<<lineSeparator
                sb<<"\t\t\t\t\tfor(int i = 0; i < array.length(); i++){"<<lineSeparator
                sb<<"\t\t\t\t\t\t$subtype asub = ($subtype) array.opt(i);"<<lineSeparator
                sb<<"\t\t\t\t\t\t${key}.add(asub);"<<lineSeparator
                sb<<"\t\t\t\t\t}"<<lineSeparator

            } else {
                //TODO
                writeItemData2File(subtype, value[0])
                sb<<"\t\t\t\t\t${key} = ${subtype}.createWithJsonArray(array);"<<lineSeparator
                sb<<"\t\t\t\t}"<<lineSeparator
            }

        } 


        else if(type.equals("long") || type.equals("int")
            || type.equals("String") || type.equals("boolean") || type.equals("double")){
            
            type = type.capitalize()
            sb<<"\t\t\t\tif(!json.isNull(\"${key}\")) $key = json.opt${type}(\"$result\");"<<lineSeparator            
        } 


        else {
            
            key = changeResultKey(key);
            //create the Item's JavaBean
            writeItemData2File(key,value)

            //add lines to File
            sb<<lineSeparator
            
            sb<<"\t\t\t\tif(!json.isNull(\"${result}\")) {"<<lineSeparator
            sb<<"\t\t\t\t\tJSONObject sub = json.optJSONObject(\"$result\");"<<lineSeparator
            if(type.equalsIgnoreCase("result")){
                type = responseEntity.capitalize()
            }
            sb<<"\t\t\t\t\t$key = new $type(sub);"<<lineSeparator
            sb<<"\t\t\t\t}"<<lineSeparator
            sb<<lineSeparator
        }
}

def changeResultKey(fkey){
    // 如果为result对象则转为预留的参数
    if(fkey.equalsIgnoreCase("result")){
        fkey = responseEntity
    }
    return fkey;
}