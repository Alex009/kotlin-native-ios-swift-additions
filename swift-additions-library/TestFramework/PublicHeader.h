//
//  PublicHeader.h
//  TestFramework
//
//  Created by Aleksey Mikhailov on 04.06.2021.
//

#ifndef PublicHeader_h
#define PublicHeader_h

@interface MPLLiveData<T>: NSObject
- (void)addObserverObserver:(void (^ _Nonnull)(T _Nullable))observer;
@end;

#endif /* PublicHeader_h */
