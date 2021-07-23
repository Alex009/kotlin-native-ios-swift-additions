//
//  ContentView.swift
//  kswiftsample
//
//  Created by Aleksey Mikhailov on 23.07.2021.
//

import SwiftUI
import mpp_library
import KSwift

struct ContentView: View {
    var body: some View {
      Button("check!") {
        let liveData: LiveData<NSString> = LiveData(init: "test")
        let label: UILabel = UILabel()
        label.bind(liveData: liveData)
      }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
