//
//  ContentView.swift
//  AbraiOS
//
//  Created by David Nedrow on 2020-01-13.
//  Copyright © 2020 Zocalo Devs. All rights reserved.
//

import SwiftUI
import KTMulti

struct ContentView: View {
    var body: some View {
        VStack {
            Text(HelloWorld().getHello())
            Text(IosHelloWorld().getHello())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
