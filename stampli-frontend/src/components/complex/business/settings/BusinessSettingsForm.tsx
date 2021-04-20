import React from 'react';
import { ImageUpload } from '../../../concrete/upload/img/ImageUpload';
import { ContextTitledInput } from '../../../contexted/form/input/ContextTitledInput';
import { ContextForm } from '../../../contexted/form/ContextForm';

export const BusinessSettingsForm = () => (
  <>
    <ContextForm definitions={[{ name: 'businessName', initialValue: 'Twój stary' }]} onSubmit={console.log}>
      <ContextTitledInput title={'Nazwa Biznesu'} name={'businessName'} />
    </ContextForm>
    <ImageUpload />
  </>
);
