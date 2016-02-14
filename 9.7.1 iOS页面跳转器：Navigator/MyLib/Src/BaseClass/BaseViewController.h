//
//  BaseViewController.h
//  MyLib
//
//  Created by jax on 13-9-2.
//  Copyright (c) 2013年 Bao. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface BaseViewController : UIViewController {
    NSDictionary* _param;
}

@property (nonatomic, retain) NSDictionary* param;

- (void)createFields;
- (void)destroyFields;

- (void)createEvents;
- (void)destroyEvents;

- (void)createViews;
- (void)destroyViews;

- (void)loadData;

@end
