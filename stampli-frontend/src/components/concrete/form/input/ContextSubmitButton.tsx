import React from 'react';
import { SimpleButton } from '../../buttons/SimpleButton';
import { FormSubmitContext } from '../ContextForm';

export const ContextSubmitButton = ({ text }: { text: string }) => (
  <>
    <FormSubmitContext.Consumer>
      {submit => <SimpleButton text={text} onClick={submit}></SimpleButton>}
    </FormSubmitContext.Consumer>
  </>
);
