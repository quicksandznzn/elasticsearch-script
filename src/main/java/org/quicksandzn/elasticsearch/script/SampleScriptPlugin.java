package org.quicksandzn.elasticsearch.script;


import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;
import org.elasticsearch.script.ScriptEngineService;

/**
 * Created by zhangshuai@taoche.com on 2019/3/22
 */
public class SampleScriptPlugin extends Plugin implements ScriptPlugin {

    @Override
    public ScriptEngineService getScriptEngineService(Settings settings) {
        return new SampleScriptEngineService();
    }
}
