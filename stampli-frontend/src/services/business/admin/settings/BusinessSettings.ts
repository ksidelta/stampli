export interface BusinessSettings {
  name: string | undefined;
  imageUrl: string | undefined;

  updateName(name: string): void;

  updateImage(file: File, imageUrl: string): void;
}
