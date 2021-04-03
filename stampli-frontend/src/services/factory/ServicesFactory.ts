import { ServicesBundle } from '../ServicesBundle';

export interface ServicesFactory {
  create(): ServicesBundle;
}
