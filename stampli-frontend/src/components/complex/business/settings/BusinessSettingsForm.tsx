import React from 'react';
import { ImageUpload } from '../../../simple/upload/img/ImageUpload';
import { TitledInputComponent } from '../../../simple/form/input/TitledInputComponent';

export const BusinessSettingsForm = ({
  businessName,
  businessNameOnChange,
  imageUrl,
  imageOnChange
}: {
  businessName: string;
  businessNameOnChange: (value: string) => void;
  imageUrl: string | undefined;
  imageOnChange: (file: File, url: string) => void;
}) => (
  <>
    <TitledInputComponent title={'Nazwa Biznesu'} type={'text'} onChange={businessNameOnChange} value={businessName} />
    <ImageUpload onUpload={imageOnChange} imageUrl={imageUrl} />
  </>
);
