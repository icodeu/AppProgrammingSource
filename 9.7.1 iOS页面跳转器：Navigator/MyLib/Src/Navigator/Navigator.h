//
//  Navigator.h
//  ElongClient
//
//  Created by Jianqiang on 13-11-15.
//  Copyright (c) 2013年. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Navigator : NSObject {

}

+ (Navigator *)sharedInstance;

+ (void)navigateTo:(NSString *)viewController;

+ (void)navigateTo:(NSString *)viewController
          withData:(NSDictionary *)param;

@end
