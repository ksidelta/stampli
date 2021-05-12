import React from 'react';
import { BusinessSettingsForm } from '../../../complex/business/settings/BusinessSettingsForm';
import { InjectionContext } from '../../context/InjectionContext';
import { ServicesBundle } from '../../../../services/ServicesBundle';
import { Observer } from 'mobx-react';

export const StatedBusinessSettingsForm = () => (
  <InjectionContext.Consumer>
    {({ businessSettings }: ServicesBundle) => (
      <Observer>
        {() => (
          <BusinessSettingsForm
            businessName={businessSettings.name || ''}
            businessNameOnChange={name => businessSettings.updateName(name)}
            imageUrl={businessSettings.imageUrl}
            imageOnChange={(file, url) => businessSettings.updateImage(file, url)}
          />
        )}
      </Observer>
    )}
  </InjectionContext.Consumer>
);
