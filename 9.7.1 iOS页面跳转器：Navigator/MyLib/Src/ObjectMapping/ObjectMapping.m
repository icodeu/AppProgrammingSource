#import "ObjectMapping.h"
#import "ObjectMappingEntity.h"

@implementation ObjectMapping

@synthesize typeOfClass = _typeOfClass;
@synthesize mappings = _mappings;

#pragma mark - class methods

+ (ObjectMapping *)mappingForClass:(Class)typeOfClass {
    ObjectMapping *mapping = [ObjectMapping new];
    mapping.typeOfClass = typeOfClass;
    return [mapping autorelease];
}

#pragma mark - instance methods

- (id)init {
    self = [super init];
    if (self) {
        _mappings = [NSMutableArray new];
    }
    return self;
}

- (void)dealloc {
    [_mappings release];
    [super dealloc];
}

- (void)converEntityFromJsonToEntity:(NSString *)from to:(NSString *)to withClass:(NSString*)className {
    ObjectMappingEntity *entity = [ObjectMappingEntity new];
    entity.from = from;
    entity.to = to;
    entity.typeOfClass = NSClassFromString(className);
    
    if([className isEqualToString: @"NSString"] || [className isEqualToString: @"NSNumber"])
        entity.isSimpleType = YES;
    else
        entity.isSimpleType = NO;
    
    [self.mappings addObject:entity];
    [entity release];
}

@end
