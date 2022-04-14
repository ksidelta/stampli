import React from 'react';
import { ImageUploadComponent } from './ImageUploadComponent';
import { EventDrivenImageUpload } from './EventDrivenImageUpload';
import { ImageValue } from './ImageValue';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/producers/input/InputEvent';
import { StateForImageUpload } from './StateForImageUpload';
import { InputState } from '../../../../state/form/input/InputState';

export const ImageUpload = ({
  topic,
  state
}: {
  topic: Subject<InputEvent<ImageValue>>;
  state: InputState<ImageValue>;
}) => (
  <StateForImageUpload state={state}>
    {imageState => (
      <EventDrivenImageUpload topic={topic}>
        {consumer => (
          <ImageUploadComponent
            onUpload={(buffer, url) => consumer(new ImageValue(buffer, url))}
            imageUrl={imageState.imageUrl}
          />
        )}
      </EventDrivenImageUpload>
    )}
  </StateForImageUpload>
);
