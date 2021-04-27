#pragma once

#include "core/object.h"

class PluginClass : public Object {
    GDCLASS(RateMe, Object);
    
    static void _bind_methods();
    
public:
    void showRateMe ();
    
    PluginClass();
    ~PluginClass();
};


