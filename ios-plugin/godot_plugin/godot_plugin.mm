#import <Foundation/Foundation.h>
#import "godot_plugin.h"
#import "godot_plugin_class.h"
#import "core/engine.h"

PluginClass *plugin;

void rateme_init() {
    plugin = memnew(PluginClass);
    Engine::get_singleton()->add_singleton(Engine::Singleton("RateMe", plugin));
}

void rateme_deinit() {
   if (plugin) {
       memdelete(plugin);
   }
}
