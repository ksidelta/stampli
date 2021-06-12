import { BusinessSettings } from './BusinessSettings';

export class BasicBusinessSettings implements BusinessSettings {
  constructor(public name: string | undefined, public imageUrl: string | undefined) {}

  updateImage(file: File, imageUrl: string): void {
    this.imageUrl = imageUrl;
  }

  updateName(name: string): void {
    this.name = name;
  }
}
