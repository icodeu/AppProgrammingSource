// JSON iOS Class Generator
// Written by Bruce Bao

#import <Foundation/Foundation.h>

@class ObjectMapping;
@class UserEntity;

@interface NewUserEntity : NSObject
{
    NSNumber *userId;
    UserEntity *userInfo;
}

@property (nonatomic,retain) NSNumber *userId;
@property (nonatomic,retain) UserEntity *userInfo;

- (ObjectMapping *)objectMapping;

@end