// JSON iOS Class Generator
// Written by Bruce Bao

#import "UserEntity.h"
#import "NewUserEntity.h"
#import "ObjectMapping.h"

@implementation NewUserEntity

@synthesize userId;
@synthesize userInfo;

- (ObjectMapping *)objectMapping {
    ObjectMapping *mapping = [ObjectMapping mappingForClass:[NewUserEntity class]];
    [mapping converEntityFromJsonToEntity:@"UserId" to:@"userId" withClass: @"NSNumber"];
    [mapping converEntityFromJsonToEntity:@"UserInfo" to:@"userInfo" withClass: @"UserEntity"];
    return mapping;
}

- (void)dealloc {
    [userId release];
    [userInfo release];

    [super dealloc];
}

@end
