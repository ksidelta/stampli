import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/producers/input/InputEvent';
import React from 'react';
import { InputChangedEvent } from '../../../../events/producers/input/InputChangedEvent';
import { ImageValue } from './ImageValue';

export const EventDrivenImageUpload = ({
  topic,
  children
}: {
  topic: Subject<InputEvent<ImageValue>>;
  children: (consumer: (value: ImageValue) => void) => React.ReactNode;
}) => <>{children(value => topic.next(new InputChangedEvent('name', value)))}</>;
