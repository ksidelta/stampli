import React, { useContext, useMemo, useState } from 'react';
import styled from 'styled-components';
import { MobilePage } from '../base/MobilePage';
import Header from '../../components/Header';
import Box from '@mui/material/Box';
import TextField from '../../components/TextField';
import Button from '../../components/Button';
import ButtonText from '../../components/ButtonText';
import { InputState } from '../../state/form/input/InputState';
import { TextInputStateImpl } from '../../v2/components/simple/form/input/TextInputState';
import { observer } from 'mobx-react';
import { InjectionContext } from '../../components/stated/context/InjectionContext';

const Inner = styled.div`
  height: 100vh;

  > form {
    height: calc(100% - 3.2rem);
    display: flex;
    flex-flow: column nowrap;
    justify-content: center;
    align-items: center;
    margin: unset;
  }
`;

export default observer(() => {
    const email = useMemo(() => TextInputStateImpl.create(''), []);
    const password = useMemo(() => TextInputStateImpl.create(''), []);
    const mainContext = useContext(InjectionContext);

    return (
      <MobilePage>
        <Inner>
          <Header title='Zaloguj Się (Email)' />
          <Box
            component='form'
            autoComplete='on'
          >
            <TextField text='Adres Email' value={email.text()} setValue={t => email.setText(t)} required={true} />
            <TextField text='Hasło' value={password.text()} setValue={t => password.setText(t)} required={true} />
            <Button text='Zaloguj Się' link='/stamples' button=''
                    onClick={() => mainContext.loginService.login(email.text(), password.text())} />
            <ButtonText text='Zarejestruj Się Adresem Email' link='/register/email' />
          </Box>
        </Inner>
      </MobilePage>
    );
  }
);