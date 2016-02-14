//
//  AppDelegate.h
//  MyApp
//
//  Created by jax on 13-9-1.
//  Copyright (c) 2013年 Bao. All rights reserved.
//

#import <UIKit/UIKit.h>

@class APageViewController;

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) UINavigationController *navigationController;

@property (strong, nonatomic) APageViewController *rootViewController;

@end
