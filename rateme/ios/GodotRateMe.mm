//
//  GodotRateMe.mm
//
//  Created by Vasiliy on 07.05.19.
//
//

#import <Foundation/Foundation.h>
#import <StoreKit/StoreKit.h>
#import "./GodotRateMe.h"

GodotRateMe::GodotRateMe()
{
}

GodotRateMe::~GodotRateMe()
{
}

void GodotRateMe::showRateMe()
{
    [SKStoreReviewController requestReview];
}

void GodotRateMe::_bind_methods()
{
    ClassDB::bind_method(D_METHOD("showRateMe"), &GodotRateMe::showRateMe);
}
