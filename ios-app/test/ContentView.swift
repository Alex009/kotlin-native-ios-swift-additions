//
//  ContentView.swift
//  test
//
//  Created by Aleksey Mikhailov on 02.06.2021.
//

import SwiftUI
import library
import TestFramework

let hello = LiveData<NSString>()
let test = SwiftAddition()

struct ContentView: View {
    var body: some View {
      Button("Hi!") {
        hello.stringLd()
        hello.intLd()
        print(test.getValue())
      }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
