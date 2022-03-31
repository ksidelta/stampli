import React from 'react';
import { Header } from '../components/simple/Header';
import { MobilePage } from './base/MobilePage';
import { IconButton } from '../components/simple/buttons/IconButton';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons/faArrowRight';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { Content } from '../components/simple/container/layout/structure/Content';

export const EntryPage = () => (
  <MobilePage>
    <Header title={'W czym mogę Ci dzisiaj pomóc?'} icon={faCircle} />
    <Content>
      <IconButton
        text={'Zeskanuj pieczątke!'}
        icon={faArrowRight}
        onClick={history => history.goToScanner()}
      />
      <IconButton
        text={'Chcę rozdawać pieeeczątki!'}
        icon={faArrowRight}
        onClick={history => history.goToBusinessOptionsPage()}
      />
    </Content>
  </MobilePage>
);
