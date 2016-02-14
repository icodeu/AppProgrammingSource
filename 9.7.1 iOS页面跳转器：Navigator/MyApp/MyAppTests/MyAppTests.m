//
//  MyAppTests.m
//  MyAppTests
//
//  Created by jax on 13-9-6.
//  Copyright (c) 2013年 Bao. All rights reserved.
//

#import "MyAppTests.h"
#import "SBJsonParser.h"
#import "NSString+SBJSON.h"
#import "ObjectMappingLoader.h"
#import "UserEntity.h"
#import "UserWrapEntity.h"
#import "NewUserEntity.h"

@implementation MyAppTests

- (void)setUp
{
    [super setUp];
    
    // Set-up code here.
}

- (void)tearDown
{
    // Tear-down code here.
    
    [super tearDown];
}

- (void)testUserEntity
{
    NSString* jsonStr = @"{\"userName\":\"baobao\", \"userAge\":18}";
    id jsonValue = [jsonStr JSONValue];
    id result = [ObjectMappingLoader loadObjectWithClassName: @"UserEntity" andData:jsonValue];
    UserEntity* user = (UserEntity*)result;
    STAssertTrue([user.name isEqualToString: @"baobao"], nil);
    STAssertTrue([user.age intValue] == 18, nil);
}



- (void)testNewUserEntity
{
    NSString* jsonStr = @"{\"UserId\": 1, \"UserInfo\": {\"userName\": \"baobao\", \"userAge\": 18}}";
    id jsonValue = [jsonStr JSONValue];
    id result = [ObjectMappingLoader loadObjectWithClassName: @"NewUserEntity" andData:jsonValue];
    NewUserEntity* newUser = (NewUserEntity*)result;
    
    STAssertTrue([newUser.userId intValue] == 1, nil);
    STAssertTrue([newUser.userInfo.age intValue] == 18, nil);
    STAssertTrue([newUser.userInfo.name isEqualToString: @"baobao"], nil);
}

- (void)testStandardUserArrayEntity
{
    NSString* jsonStr = @"{\"Users\": [{\"userName\":\"baobao\", \"userAge\":18}, {\"userName\":\"baobao2\", \"userAge\":17}]}";
    id jsonValue = [jsonStr JSONValue];
    UserWrapEntity* result = (UserWrapEntity*)[ObjectMappingLoader loadObjectWithClassName: @"UserWrapEntity" andData:jsonValue];
    
    for(UserEntity* user in result.users) {
        if([user.name isEqualToString: @"baobao"]) {
            STAssertTrue([user.age intValue] == 18, nil);
        } else if([user.name isEqualToString: @"baobao2"]) {
            STAssertTrue([user.age intValue] == 17, nil);
        }
    }
}

- (void)testNotStandardUserArrayEntity
{
    NSString* jsonStr = @"[{\"userName\":\"baobao\", \"userAge\":18}, {\"userName\":\"baobao2\", \"userAge\":17}]";
    id jsonValue = [jsonStr JSONValue];
    id result = [ObjectMappingLoader loadObjectWithClassName: @"UserEntity" andData:jsonValue];
    for(UserEntity* user in result) {
        if([user.name isEqualToString: @"baobao"]) {
            STAssertTrue([user.age intValue] == 18, nil);
        } else if([user.name isEqualToString: @"baobao2"]) {
            STAssertTrue([user.age intValue] == 17, nil);
        }
    }
}

@end
