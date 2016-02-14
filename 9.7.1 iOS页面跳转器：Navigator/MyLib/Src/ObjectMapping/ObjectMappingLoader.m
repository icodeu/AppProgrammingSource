#import "ObjectMappingLoader.h"
#import "ObjectMapping.h"
#import "ObjectMappingEntity.h"

@implementation ObjectMappingLoader

+ (id)loadObjectWithClassName:(NSString *)className andData:(id)jsonData {
    if (nil == className|| [className length] <= 0 || nil == jsonData)
        return nil;
    
    id result = [[NSClassFromString(className) new] autorelease];
    ObjectMapping *mapping = nil;
    if ([result respondsToSelector:@selector(objectMapping)]) {
        mapping = [result performSelector:@selector(objectMapping)];
    }
    else {
        NSLog(@"loadObject Error: no such class %@", className);
    }
    
    if (nil != mapping) {
        if ([jsonData isKindOfClass:[NSDictionary class]])
            //转换正规JSON字符串
            result =  [self assignObject:result mapping:mapping dictionary:jsonData];
        else
            //转换非标准JSON字符串，即一个数组
            result = [self loadArrayFromClass:NSClassFromString(className) andArray:jsonData];
    }
    
    return result;
}

+ (id)assignObject:(id)object mapping:(ObjectMapping *)mapping dictionary:(NSDictionary *)dic {
    for (ObjectMappingEntity *entity in mapping.mappings) {
        id value = [dic objectForKey:entity.from];

        //以下代码用于转换简单类型，NSNumber和NSString
        if (entity.isSimpleType == YES) {
            [object setValue:value forKey:entity.to];
            continue;
        }
        
        //以下代码用于转换复杂类型，该复杂实体是一个单实体
        if ([value isKindOfClass:[NSDictionary class]]) {
            id result = [[[entity.typeOfClass alloc] init] autorelease];
            ObjectMapping *rMap = nil;
            if ([result respondsToSelector:@selector(objectMapping)])
                rMap = [result performSelector:@selector(objectMapping)];
            [self assignObject:result mapping:rMap dictionary:value];
            [object setValue:result forKey:entity.to];
            continue;
        }
        
        //以下代码用于转换复杂类型，该复杂实体是一个数组
        if ([value isKindOfClass:[NSArray class]]) {
            NSArray *array = [self loadArrayFromClass:entity.typeOfClass andArray:value];
            if (array != nil)
                [object setValue:array forKey:entity.to];
        }
    }
    
    return object;
}

//加载一个不规则的JSON数组
+ (NSArray *)loadArrayFromClass:(Class)objectClass andArray:(NSArray *)array {
    if ([array count] <= 0)
        return array;
    
    NSMutableArray *result = [NSMutableArray arrayWithCapacity:0];

    id item = [objectClass new];
    ObjectMapping *mapping = nil;
    
    if ([item respondsToSelector:@selector(objectMapping)])
        mapping = [item performSelector:@selector(objectMapping)];
    [item release];
    
    if (nil == mapping)
        return result;
    
    for (id element in array) {
        id item = [objectClass new];
        [self assignObject:item mapping:mapping dictionary:element];
        [result addObject:item];
        [item release];
    }
    
    return result;
}

@end