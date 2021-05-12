import React from 'react';
import { SimpleButton } from '../../../simple/buttons/SimpleButton';
import { FormSetValueContext, FormSubmitContext } from '../ContextForm';

export const ContextSubmitButton = ({ text, value }: { text: string; value?: any }) => (
  <>
    <FormSetValueContext.Consumer>
      {setValue => (
        <FormSubmitContext.Consumer>
          {submit => <SimpleButton text={text} onClick={() => setValue('submit', value).then(x => submit())} />}
        </FormSubmitContext.Consumer>
      )}
    </FormSetValueContext.Consumer>
  </>
);
