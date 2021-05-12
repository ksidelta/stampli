import React from 'react';
import { Subject } from 'rxjs';
import { StringValue } from '../../../../events/form/input/value/StringValue';
import { InputEvent } from '../../../../events/form/input/InputEvent';
import { InputChangedEvent } from '../../../../events/form/input/InputChangedEvent';

export const EventDrivenTextInput = ({
  z
  children
}: {
  topic: Subject<InputEvent<StringValue>>;
  children: (consumer: (value: string) => void) => React.ReactNode;
}) => <>{children(value => topic.next(new InputChangedEvent('name', new StringValue(value))))}</>;
