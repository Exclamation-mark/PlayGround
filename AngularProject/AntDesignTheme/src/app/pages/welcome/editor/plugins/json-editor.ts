import {API, BlockTool, BlockToolConstructorOptions} from '@editorjs/editorjs';

export class JsonEditor implements BlockTool {
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
      title: 'Json',
      icon: '<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">' +
        '  <path stroke-linecap="round" stroke-linejoin="round" d="M10 20l4-16m4 4l4 4-4 4M6 16l-4-4 4-4" />' +
        '</svg>'
    };
  }

  destroy(): void {
    console.log('zzq see json Destroy');
  }

  render(): HTMLElement {
    this.wrapper = document.createElement('div');
    const input = document.createElement('monaco-input-component');
    this.wrapper.appendChild(input);
    return this.wrapper;
  }


  save(blockContent: HTMLElement): any {
    return {
      json: {zzq: 'hha'}
    };
  }

  validate(savedData: any): any {
    console.log('zzq see saved', savedData);
    return true;
  }
}