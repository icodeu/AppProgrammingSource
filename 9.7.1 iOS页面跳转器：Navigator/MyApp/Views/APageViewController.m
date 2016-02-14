//
//  APageViewController.m
//  MyApp
//
//  Created by jax on 13-9-2.
//  Copyright (c) 2013年 Bao. All rights reserved.
//

#import "APageViewController.h"
#import "SBJsonParser.h"
#import "NSString+SBJSON.h"
#import "ASIHTTPRequest.h"
#import "ObjectMappingLoader.h"
#import "WeatherEntity.h"
#import "Navigator.h"
#import "BViewController.h"

@interface APageViewController () {
    UILabel* nameLabel;
    UILabel* ageLabel;
    UIButton* getInfoButton;
    UIButton* clearInfoButton;
    UIButton* jump1Button;
    UIButton* jump2Button;
}

@end

@implementation APageViewController

- (void) jumpTo2 {
    NSMutableDictionary* dict =
        [NSMutableDictionary dictionary];
    [dict setObject: @"5.7.1" forKey:@"version"];
    
    [Navigator navigateTo: @"BViewController"
                 withData: dict];
}


- (void) jumpTo1 {
    BViewController* b = [[BViewController alloc] init];
    b.version = @"5.7.1";
    [self.navigationController
     pushViewController: b animated:YES];
    
    [b release];
}

@end
