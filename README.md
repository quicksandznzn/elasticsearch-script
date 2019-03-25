# elasticsearch-script



-- elasticsearch 5.6.14

~~implements NativeScriptFactory~~

implements ScriptEngineService

1. mvn clean install

2. ./elasticsearch-plugin install file:///Users/zn/code/spring-demo/spring-boot-sample/elasticsearch-script/target/releases/elasticsearch-script-1.0.0.zip

3. brew services restart elasticsearch@5.6

4. post query  http://127.0.0.1:9200/index/_search
```json
{ 
    "query":{
        "function_score":{
            "query":{
                "match_all":{}
            },
            "script_score":{
                "script":{
                    "source":"field_value_len_sort",
                    "lang":"sample_script",
                    "params":{
                        "field":"name"
                    }
                }
            }
        }
    }
}
 
```
