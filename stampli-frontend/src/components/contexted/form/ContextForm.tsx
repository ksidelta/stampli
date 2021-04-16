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

export const FormSetValueContext = React.createContext<(name: string, value: any) => Promise<void>>(() => {
  throw new Error('no callback');
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
  onSubmit: (values: Record<string, any>) => void;
  children: ReactNode[];
}) => (
  <>
    <Formik
      initialValues={Object.fromEntries(definitions.map(x => [x.name, x.initialValue ?? '']))}
      onSubmit={onSubmit}>
      {(props: {
        values: Record<string, string>;
        handleSubmit: () => void;
        handleChange: (e: React.ChangeEvent<any>) => void;
        setFieldValue: (name: string, value: any) => Promise<void>;
      }) => (
        <FormValuesContext.Provider value={props.values}>
          <FormSubmitContext.Provider value={props.handleSubmit}>
            <FormOnChangeContext.Provider value={props.handleChange}>
              <FormSetValueContext.Provider value={props.setFieldValue}>{children}</FormSetValueContext.Provider>
            </FormOnChangeContext.Provider>
          </FormSubmitContext.Provider>
        </FormValuesContext.Provider>
      )}
    </Formik>
  </>
);
