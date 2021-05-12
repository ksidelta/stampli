import React from 'react';
import ReactImageUploading, { ImageListType } from 'react-images-uploading';
import styled from 'styled-components';
import Rectangle from 'react-rectangle';
import { BasicLabel } from '../../label/BasicLabel';
import { Center } from '../../container/layout/align/Center';
import { CenterMiddle } from '../../container/layout/CenterMiddle';

export const ImageUpload = ({ onUpload, imageUrl }: { onUpload: OnUpload; imageUrl: string | undefined }) => {
  const [images, setImages] = React.useState<ImageListType>([]);

  return (
    <div>
      <BasicLabel>{'Ustaw obrazek dla pieczątek'}</BasicLabel>
      <Center>
        <ReactImageUploading
          value={images}
          onChange={list => {
            setImages(list);
            list[0].file && list[0].dataURL && onUpload(list[0].file, list[0].dataURL);
          }}>
          {({ onImageUpload }) => (
            <ImageHolder onClick={onImageUpload}>
              <Rectangle aspectRatio={[1, 1]}>
                <CenterMiddle>{imageUrl ? <img src={imageUrl} alt={'nullo'} /> : 'KURWA'}</CenterMiddle>
              </Rectangle>
            </ImageHolder>
          )}
        </ReactImageUploading>
      </Center>
    </div>
  );
};

const ImageHolder = styled.div`
  width: 60%;
  border: 1px solid black;

  & img {
    max-width: 100%;
    max-height: 100%;
  }
`;

export type OnUpload = (file: File, url: string) => void;
