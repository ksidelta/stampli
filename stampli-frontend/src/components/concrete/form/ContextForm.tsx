import React, { ReactNode } from 'react';
import { Formik } from 'formik';

export type FieldDefinitions = {
  name: string;
  initialValue?: string;
  validator?: (value: string) => boolean;
}[];

export const FormValuesContext = React.createContext<Record<string, string>>({});

export const FormOnChangeContext = React.createContext<(e: React.ChangeEvent<any>) => void>(() => {
  throw new Error('no on change callback');
});

export const FormSubmitContext = React.createContext<() => void>(() => {
  throw new Error('no submit callback');
});

export const ContextForm = ({
  definitions,
  onSubmit,
  children
}: {
  definitions: FieldDefinitions;
  onSubmit: (values: Record<string, string>) => void;
  children: ReactNode[];
}) => (
  <>
    <Formik
      initialValues={Object.fromEntries(definitions.map(x => [x.name, x.initialValue ?? '']))}
      onSubmit={onSubmit}>
      {(props: {
        values: Record<string, string>;
        submit: () => void;
        handleChange: (e: React.ChangeEvent<any>) => void;
      }) => (
        <FormValuesContext.Provider value={props.values}>
          <FormSubmitContext.Provider value={props.submit}>
            <FormOnChangeContext.Provider value={props.handleChange}>{children}</FormOnChangeContext.Provider>
          </FormSubmitContext.Provider>
        </FormValuesContext.Provider>
      )}
    </Formik>
  </>
);
