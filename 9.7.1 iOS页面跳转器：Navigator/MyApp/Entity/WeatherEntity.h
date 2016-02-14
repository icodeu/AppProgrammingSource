// JSON iOS Class Generator
// Written by Bruce Bao

#import <Foundation/Foundation.h>

@class ObjectMapping;
@class WeatherEntity;

@interface WeatherWrapEntity : NSObject
{
    WeatherEntity *weatherInfo;
}

@property (nonatomic,retain) WeatherEntity *weatherInfo;

- (ObjectMapping *)objectMapping;

@end

@interface WeatherEntity : NSObject
{
    NSString *radar;
    NSString *sd;
    NSString *wd;
    NSString *ws;
    NSNumber *wse;
    NSString *city;
    NSNumber *cityId;
    NSNumber *isRadar;
    NSNumber *temp;
    NSString *time;
}

@property (nonatomic,retain) NSString *radar;
@property (nonatomic,retain) NSString *sd;
@property (nonatomic,retain) NSString *wd;
@property (nonatomic,retain) NSString *ws;
@property (nonatomic,retain) NSNumber *wse;
@property (nonatomic,retain) NSString *city;
@property (nonatomic,retain) NSNumber *cityId;
@property (nonatomic,retain) NSNumber *isRadar;
@property (nonatomic,retain) NSNumber *temp;
@property (nonatomic,retain) NSString *time;

- (ObjectMapping *)objectMapping;

@end