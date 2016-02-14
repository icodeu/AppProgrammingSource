// JSON iOS Class Generator
// Written by Bruce Bao

#import "UserWrapEntity.h"
#import "UserEntity.h"
#import "ObjectMapping.h"

@implementation UserWrapEntity

@synthesize users;

- (ObjectMapping *)objectMapping {
    ObjectMapping *mapping = [ObjectMapping mappingForClass:[UserWrapEntity class]];
    [mapping converEntityFromJsonToEntity:@"Users" to:@"users" withClass: @"UserEntity"];
    return mapping;
}

- (void)dealloc {
    [users release];

    [super dealloc];
}

@end
