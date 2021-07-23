Pod::Spec.new do |spec|
    spec.name                     = 'KSwift'
    spec.version                  = '0.1.0'
    spec.homepage                 = 'https://github.com/Alex009'
    spec.source                   = { :git => "Not Published", :tag => "Cocoapods/#{spec.name}/#{spec.version}" }
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Kotlin Swiftify plugin sample'

    spec.static_framework         = true
    spec.dependency 'mpp_library'

    spec.source_files = "build/bin/ios/AdditionsSources/**/*.{h,m,swift}"
end