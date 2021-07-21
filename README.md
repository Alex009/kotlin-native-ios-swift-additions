# Swift additions inside kotlin library

**This is experimental project.**

# Notes
- add rpath `install_name_tool -add_rpath /usr/lib/swift library` in `mpp-library/build/bin/ios/debugFramework/library.framework`

# Links
- https://developer.apple.com/forums/thread/115551
- https://github.com/JetBrains/kotlin-native/issues/2555#issuecomment-455564055
- https://forums.swift.org/t/where-to-place-custom-module-modulemap-for-third-party-c-library-without-touching-their-source-code/17920/9
- https://clang.llvm.org/docs/Modules.html#module-maps
- https://github.com/apple/swift/tree/main/test/ModuleInterface
- https://cdmana.com/2021/04/20210401211248805f.html
- https://developer.apple.com/forums/thread/123253
- https://forums.swift.org/t/explicit-module-builds-the-new-swift-driver-and-swiftpm/36990/17