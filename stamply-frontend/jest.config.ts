// jest.config.ts
import type { Config } from '@jest/types';

// Sync object
const config: Config.InitialOptions = {
  verbose: true,
  testEnvironment: 'node',
  roots: ['./src/', './test/'],
  preset: 'ts-jest'
};

export default config;
