//
//  GodotRateMe.h
//
//  Created by Vasiliy on 07.05.19.
//
//

#ifndef GodotRateMe_h
#define GodotRateMe_h

#include "core/object.h"

class GodotRateMe : public Object {
    GDCLASS(GodotRateMe, Object);

    static void _bind_methods();

public:
    GodotRateMe();
    ~GodotRateMe();

    void showRateMe();

};

#endif /* GodotRateMe_h */
