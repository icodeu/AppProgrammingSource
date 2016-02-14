// JSON iOS Class Generator
// Written by Bruce Bao

#import <Foundation/Foundation.h>

@class ObjectMapping;
@class UserEntity;

@interface UserWrapEntity : NSObject
{
    NSArray *users;
}

@property (nonatomic,retain) NSArray *users;

- (ObjectMapping *)objectMapping;

@end