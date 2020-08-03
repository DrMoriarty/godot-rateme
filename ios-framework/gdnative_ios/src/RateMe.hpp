//
//  GodotRateMe.h
//
//  Created by Vasiliy on 07.05.19.
//
//

#ifndef GodotRateMe_h
#define GodotRateMe_h

#include <Godot.hpp>
#include <Object.hpp>

class RateMe : public godot::Object {
    GODOT_CLASS(RateMe, godot::Object);

public:
    RateMe();
    ~RateMe();

    static void _register_methods();
    void _init();

    void showRateMe();

};

#endif /* GodotRateMe_h */
