package elasticsearch.script;


import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;

/**
 * Created by zhangshuai@taoche.com on 2019/3/22
 */
public class SampleScriptPlugin extends Plugin implements ScriptPlugin {

    @Override
    public org.elasticsearch.script.ScriptEngineService getScriptEngineService(Settings settings) {
        return new SampleScriptEngineService();
    }
}
