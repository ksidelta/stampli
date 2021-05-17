import React from 'react';
import { TitledInputComponent } from '../../../simple/form/input/TitledInputComponent';
import { ImageUploadComponent } from '../../../simple/form/img/ImageUploadComponent';

export const BusinessSettingsForm = ({
  businessName,
  businessNameOnChange,
  imageUrl,
  imageOnChange
}: {
  businessName: string;
  businessNameOnChange: (value: string) => void;
  imageUrl: string | undefined;
  imageOnChange: (file: ArrayBuffer, url: string) => void;
}) => (
  <>
    <TitledInputComponent title={'Nazwa Biznesu'} type={'text'} onChange={businessNameOnChange} value={businessName} />
    <ImageUploadComponent onUpload={imageOnChange} imageUrl={imageUrl} />
  </>
);
