//
//  Navigator.m
//
//  Created by Jianqiang on 13-11-15.
//  Copyright (c) 2013年. All rights reserved.
//

#import "Navigator.h"
#import "BaseViewController.h"
#import "SynthesizeSingleton.h"

@implementation Navigator

SYNTHESIZE_SINGLETON_FOR_CLASS(Navigator);

+ (void)navigateTo:(NSString *)viewController {
    [self navigateTo:viewController withData:nil];
}

+ (void)navigateTo:(NSString *)viewController
          withData:(NSDictionary *)param {
    BaseViewController * classObject = (BaseViewController *)
        [[NSClassFromString(viewController) alloc] init];
    classObject.param = param;
        
    [classObject.navigationController
        pushViewController:classObject animated:YES];

    [classObject release];
}

@end
