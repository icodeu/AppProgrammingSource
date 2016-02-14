// JSON iOS Class Generator
// Written by Bruce Bao

#import "UserEntity.h"
#import "ObjectMapping.h"

@implementation UserEntity

@synthesize name;
@synthesize age;

- (ObjectMapping *)objectMapping {
    ObjectMapping *mapping = [ObjectMapping mappingForClass:[UserEntity class]];
    [mapping converEntityFromJsonToEntity:@"userName" to:@"name" withClass: @"NSString"];
    [mapping converEntityFromJsonToEntity:@"userAge" to:@"age" withClass: @"NSNumber"];
    return mapping;
}

- (void)dealloc {
    [name release];
    [age release];

    [super dealloc];
}

@end
