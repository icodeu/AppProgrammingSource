// JSON iOS Class Generator
// Written by Bruce Bao

#import "WeatherEntity.h"
#import "ObjectMapping.h"

@implementation WeatherWrapEntity

@synthesize weatherInfo;

- (ObjectMapping *)objectMapping {
    ObjectMapping *mapping = [ObjectMapping mappingForClass:[WeatherWrapEntity class]];
    [mapping converEntityFromJsonToEntity:@"weatherinfo" to:@"weatherInfo" withClass: @"WeatherEntity"];
    return mapping;
}

- (void)dealloc {
    [weatherInfo release];
    
    [super dealloc];
}

@end

@implementation WeatherEntity

@synthesize radar;
@synthesize sd;
@synthesize wd;
@synthesize ws;
@synthesize wse;
@synthesize city;
@synthesize cityId;
@synthesize isRadar;
@synthesize temp;
@synthesize time;

- (ObjectMapping *)objectMapping {
    ObjectMapping *mapping = [ObjectMapping mappingForClass:[WeatherEntity class]];
    [mapping converEntityFromJsonToEntity:@"Radar" to:@"radar" withClass: @"NSString"];
    [mapping converEntityFromJsonToEntity:@"SD" to:@"sd" withClass: @"NSString"];
    [mapping converEntityFromJsonToEntity:@"WD" to:@"wd" withClass: @"NSString"];
    [mapping converEntityFromJsonToEntity:@"WS" to:@"ws" withClass: @"NSString"];
    [mapping converEntityFromJsonToEntity:@"WSE" to:@"wse" withClass: @"NSNumber"];
    [mapping converEntityFromJsonToEntity:@"city" to:@"city" withClass: @"NSString"];
    [mapping converEntityFromJsonToEntity:@"cityid" to:@"cityId" withClass: @"NSNumber"];
    [mapping converEntityFromJsonToEntity:@"isRadar" to:@"isRadar" withClass: @"NSNumber"];
    [mapping converEntityFromJsonToEntity:@"temp" to:@"temp" withClass: @"NSNumber"];
    [mapping converEntityFromJsonToEntity:@"time" to:@"time" withClass: @"NSString"];
    
    return mapping;
}

- (void)dealloc {
    [radar release];
    [sd release];
    [wd release];
    [ws release];
    [wse release];
    [city release];
    [cityId release];
    [isRadar release];
    [temp release];
    [time release];

    [super dealloc];
}

@end
