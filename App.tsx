/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import MindboxSdk from 'mindbox-sdk';
import React, {useCallback, useEffect, useState} from 'react';
import {
  Button,
  Clipboard,
  Platform,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

const Section: React.FC<{
  title: string;
}> = ({children, title}) => {
  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
};

const App = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const [deviceUUID, setDeviceUUID] = useState('');

  const appInitializationCallback = useCallback(async () => {
    try {
      await MindboxSdk.initialize({
        domain: 'api.mindbox.ru',
        endpointId:
          Platform.OS === 'ios'
            ? 'mpush-test-ios-sandbox-docs'
            : 'mpush-test-android-sandbox-docs',
        subscribeCustomerIfCreated: true,
        shouldCreateCustomer: true,
        previousInstallId: '',
        previousUuid: '',
      });
    } catch (error) {
      console.log(error);
    }
  }, []);

  MindboxSdk.getDeviceUUID(device => setDeviceUUID(device));

  useEffect(() => {
    appInitializationCallback();
  }, [appInitializationCallback]);

  const copyToClipboard = () => {
    Clipboard.setString(deviceUUID);
    console.log(deviceUUID);
  };

  // Handling of push click for navigation
  MindboxSdk.onPushClickReceived((pushClickRecievedData: string) => {
    console.log(pushClickRecievedData);
  });
  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <Section title=" Your Mindbox DeviceUUID">
            <Text style={styles.highlight}>{deviceUUID}</Text>

            <Button
              onPress={copyToClipboard}
              title="Copy"
              color="#841584"
              accessibilityLabel="Copy Mindbox DeviceUUID"
            />
          </Section>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
