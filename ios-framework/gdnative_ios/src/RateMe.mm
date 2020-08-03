//
//  GodotRateMe.mm
//
//  Created by Vasiliy on 07.05.19.
//
//

#import <Foundation/Foundation.h>
#import <StoreKit/StoreKit.h>
#import "RateMe.hpp"

RateMe::RateMe()
{
}

RateMe::~RateMe()
{
}

void RateMe::_init()
{
}

void RateMe::showRateMe()
{
    [SKStoreReviewController requestReview];
}

void RateMe::_register_methods()
{
    register_method("showRateMe", &RateMe::showRateMe);
}
