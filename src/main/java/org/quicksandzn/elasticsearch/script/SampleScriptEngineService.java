package org.quicksandzn.elasticsearch.script;


import org.apache.lucene.index.LeafReaderContext;
import org.elasticsearch.script.*;
import org.elasticsearch.search.lookup.LeafSearchLookup;
import org.elasticsearch.search.lookup.SearchLookup;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshuai@taoche.com on 2019/3/22
 */
public class SampleScriptEngineService implements ScriptEngineService {

    @Override
    public String getType() {
        return "sample_script";
    }

    /**
     * 编译脚本function 用于search调用打分
     *
     * @param scriptName
     * @param scriptSource
     * @param params
     * @return
     */
    @Override
    public Object compile(String scriptName, String scriptSource, Map<String, String> params) {
        if ("field_value_len_sort".equals(scriptSource)) {
            return scriptSource;
        }
        throw new IllegalArgumentException("Unknown script name " + scriptSource);
    }

    @Override
    public ExecutableScript executable(CompiledScript compiledScript, Map<String, Object> vars) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SearchScript search(CompiledScript compiledScript, SearchLookup lookup, Map<String, Object> vars) {
        final String field;
        if (vars == null || vars.containsKey("field") == false) {
            throw new IllegalArgumentException("Missing parameter [field]");
        } else {
            field = (String) vars.get("field");
        }

        return new SearchScript() {
            @Override
            public LeafSearchScript getLeafSearchScript(LeafReaderContext context) throws IOException {
                final LeafSearchLookup leafLookup = lookup.getLeafSearchLookup(context);

                return new LeafSearchScript() {
                    @Override
                    public void setDocument(int doc) {
                        if (leafLookup != null) {
                            leafLookup.setDocument(doc);
                        }
                    }

                    @Override
                    public double runAsDouble() {
                        long values = 0;
                        // 根据字段长度打分
                        for (Object v : (List<?>) leafLookup.doc().get(field)) {
                            values = v.toString().length();
                        }
                        return values;
                    }
                };
            }

            @Override
            public boolean needsScores() {
                return false;
            }
        };
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public String getExtension() {
        return null;
    }

    @Override
    public boolean isInlineScriptEnabled() {
        return true;
    }
}
