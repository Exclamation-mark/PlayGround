import {API, BlockTool, BlockToolConstructorOptions} from '@editorjs/editorjs';

export class CustomImage implements BlockTool {
  private wrapper: HTMLElement;
  private api: API;
  private readOnly: boolean;
  private data: any;

  constructor({data, api, readOnly}: BlockToolConstructorOptions<any>) {
    this.data = data;
    this.api = api;
    this.readOnly = readOnly;
  }

  static get isReadOnlySupported(): boolean {
    return true;
  }

  static get toolbox(): any {
    return {
      title: 'Image',
      icon: '<svg width="17" height="15" viewBox="0 0 336 276" xmlns="http://www.w3.org/2000/svg"><path d="M291 150V79c0-19-15-34-34-34H79c-19 0-34 15-34 34v42l67-44 81 72 56-29 42 30zm0 52l-43-30-56 30-81-67-66 39v23c0 19 15 34 34 34h178c17 0 31-13 34-29zM79 0h178c44 0 79 35 79 79v118c0 44-35 79-79 79H79c-44 0-79-35-79-79V79C0 35 35 0 79 0z"/></svg>'
    };
  }

  render(): HTMLElement {
    this.wrapper = document.createElement('div');
    const input = document.createElement('input');

    this.wrapper.classList.add('simple-image');
    this.wrapper.appendChild(input);

    input.placeholder = 'Paste an image URL...';
    const value = this.data && this.data.url ? this.data.url : '';
    input.value = value;
    if (value) {
      this._createImage(value);
    }
    input.addEventListener('paste', (event: ClipboardEvent) => {
      if (event !== null) {
        // @ts-ignore
        const data = event.clipboardData.getData('text');
        if (data) {
          this._createImage(data);
        }
      }
    });
    return this.wrapper;
  }

  _createImage(url: string): void {
    const image = document.createElement('img');
    const caption = document.createElement('input');

    image.style.maxWidth = '100%';
    image.style.marginBottom = '15px';
    image.src = url;
    caption.placeholder = 'Caption...';

    this.wrapper.innerHTML = '';
    this.wrapper.appendChild(image);
    this.wrapper.appendChild(caption);
  }

  save(blockContent: HTMLElement): any {
    const input = blockContent.querySelector('input');
    return {
      url: 'zzq'
    };
  }

  validate(savedData: any): any {
    if (!savedData.url.trim()) {
      return false;
    }
    return true;
  }
}
