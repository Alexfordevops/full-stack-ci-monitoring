process.env.CHROME_BIN = require('puppeteer').executablePath();

module.exports = function (config) {
  config.set({
    browsers: ['ChromeHeadlessNoSandbox'],
    customLaunchers: {
      ChromeHeadlessNoSandbox: {
        base: 'ChromeHeadless',
        flags: [
          '--no-sandbox',
          '--disable-gpu',
          '--disable-dev-shm-usage',
        ],
      },
    },
    frameworks: ['jasmine'],
    reporters: ['progress', 'junit'],
    junitReporter: {
      outputDir: 'test-results',
      outputFile: 'test-results.xml',
      useBrowserName: false,
    },
  });
};
