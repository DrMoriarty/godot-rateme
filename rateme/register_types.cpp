#include "register_types.h"
#if defined(__APPLE__)
#include "ios/GodotRateMe.h"
#endif

void register_rateme_types() {
#if defined(__APPLE__)
	ClassDB::register_class<GodotRateMe>();
#endif
}

void unregister_rateme_types() {
}
